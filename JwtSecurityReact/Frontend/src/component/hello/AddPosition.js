import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { newAddPosition } from '../../services/service/adminService'; 
import './AddPosition.css';

const AddPosition = () => {
    const navigate = useNavigate();
    const token = localStorage.getItem('token');

    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [dataAdded, setDataAdded] = useState('');
    const [description, setDescription] = useState('');
    const [error, setError] = useState(null);

    console.log(token);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const newAdminPanel = { name, price, dataAdded, description };
            await newAddPosition(newAdminPanel, token); 
            navigate('/hello'); 
        } catch (err) {
            setError(err.response ? err.response.data : err.message);
        }
    };

    return (
        <div className="container">
            <h2>Add New Position</h2>
            {error && <p className="error-text">{error}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                    <input type="text" value={name} onChange={e => setName(e.target.value)} required />
                </div>
                <div>
                    <label>Price:</label>
                    <input type="number" value={price} onChange={e => setPrice(e.target.value)} required />
                </div>
                <div>
                    <label>Data Added:</label>
                    <input type="date" value={dataAdded} onChange={e => setDataAdded(e.target.value)} required />
                </div>
                <div>
                    <label>Description:</label>
                    <input type="text" value={description} onChange={e => setDescription(e.target.value)} required />
                </div>
                <button type="submit">Add Position</button>
            </form>
        </div>
    );
};

export default AddPosition;
