import React, { Component, useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Avatar } from '@mui/material';


class ClientList extends Component {

    constructor(props) {
        super(props);
        this.state = {clients: []};
        this.remove = this.remove.bind(this);
        this.getImage = this.getImage.bind(this);
    }

    componentDidMount() {
        fetch('/api/clients')
            .then(response => response.json())
            .then(data => this.setState({clients: data}));
    }

    async remove(id) {
        await fetch(`/api//clients/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedClients = [...this.state.clients].filter(i => i.id !== id);
            this.setState({clients: updatedClients});
        });
    }

    async getImage(id){
        await fetch(`/api/photo/${id}`,{
            method: 'GET'
        }).then((data) => {
            console.log(data);
            return data
        });
    }
    
    render() {
        const {clients} = this.state;
        const AvatarImag = ({avatarImg}) => <Avatar src={`data:image/jpeg;base64,${avatarImg}`} />
        const clientList = clients.map(client => {
            const [currentAvatar, setCurrentAvatar] = useState(null);
            useEffect(()=>{
                fetch(`/api/photo/${client.id}`,{
                    method: 'GET'
                }).then(setCurrentAvatar);
            })
            return <tr key={client.id}>
                <td><AvatarImag avatarId={currentAvatar.image}/></td>
                <td style={{whiteSpace: 'nowrap'}}>{client.name}</td>
                <td>{client.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/clients/" + client.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(client.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/clients/new">Add Client</Button>
                    </div>
                    <h3>Clients</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Avatar</th>
                            <th width="20%">Name</th>
                            <th width="20%">Email</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {clientList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ClientList;