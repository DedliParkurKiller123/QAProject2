import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllPositions, deletePosition} from '../../services/service/adminService';
import './AdminPanel.css'; 

const AdminPanel = ({ token }) => {
    const [positions, setPositions] = useState([]);
    const [sortConfig, setSortConfig] = useState({ key: null, direction: 'asc' });
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    token = localStorage.getItem('token'); 
    
    useEffect(() => {
        const fetchPositions = async () => {
            try {
                const response = await getAllPositions(token);
                setPositions(response.data);
                console.log(response.data);
            } catch (err) {
                setError(err.response ? err.response.data : err.message);
            }
        };
        fetchPositions();
    }, [token , navigate]);

    const handleDelete = async (idadminPanel) => {
        console.log("Deleting position with id:", idadminPanel); 
        if (idadminPanel === undefined) {
            alert("Position ID is undefined");
            return;
        }
        try {
            await deletePosition(idadminPanel, token);
            setPositions(positions.filter(position => position.idadminPanel !== idadminPanel));
        } catch (err) {
            alert(err.response ? err.response.data : err.message);
        }
    };

    const handleUpdate = (idadminPanel) => {
        navigate(`/update/${idadminPanel}`);
    };

    const handleAdd = () => {
        navigate(`/add`);
    };

    const handleSort = (key) => {
        let direction = 'asc';
        if (sortConfig.key === key && sortConfig.direction === 'asc') {
            direction = 'desc';
        }
        const sortedPositions = [...positions].sort((a, b) => {
            if (a[key] < b[key]) return direction === 'asc' ? -1 : 1;
            if (a[key] > b[key]) return direction === 'asc' ? 1 : -1;
            return 0;
        });
        setPositions(sortedPositions);
        setSortConfig({ key, direction });
    };

    return (
        <div className="container">
            <h1 className="heading">Admin Panel</h1>
            {error && <p className="error-text">{error}</p>}
            <table className="table">
                <thead>
                    <tr>
                        <th className="table-header">ID</th>
                        <th className="table-header" onClick={() => handleSort('name')}>
                        Name {sortConfig.key === 'name' ? (sortConfig.direction === 'asc' ? '↑' : '↓') : null}</th>
                        <th className="table-header">Price</th>
                        <th className="table-header" onClick={() => handleSort('dataAdded')}>
                        Date Add {sortConfig.key === 'dataAdded' ? (sortConfig.direction === 'asc' ? '↑' : '↓') : null}</th>
                        <th className="table-header">Description</th>
                        <th className="table-header">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {positions.map(position => (
                        <tr key={position.idadminPanel} className="table-row">
                            <td className="table-cell">{position.idadminPanel}</td>
                            <td className="table-cell">{position.name}</td>
                            <td className="table-cell">{position.price}</td>
                            <td className="table-cell">{position.dataAdded}</td>
                            <td className="table-cell">{position.description}</td>
                            <td className="table-cell">
                                <button className="button-delete" onClick={() => handleDelete(position.idadminPanel)}>Delete</button>
                                <button className="button-update" onClick={() => handleUpdate(position.idadminPanel)}>Update</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button className="button-add" onClick={handleAdd}>Add New Position</button>
        </div>
    );
};


export default AdminPanel;
