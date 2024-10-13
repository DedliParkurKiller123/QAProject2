import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getPositionById, updatePosition } from '../../services/service/adminService';
import './UpdatePosition.css'; 

const UpdatePosition = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const token = localStorage.getItem('token');

    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [dataAdded, setDataAdded] = useState('');
    const [description, setDescription] = useState('');
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPosition = async () => {
            try {
                const response = await getPositionById(id, token);
                const position = response.data;
                setName(position.name);
                setPrice(position.price);
                setDataAdded(position.dataAdded);
                setDescription(position.description);
            } catch (err) {
                setError(err.response ? err.response.data : err.message);
            }
        };
        fetchPosition();
    }, [id, token]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const updatedAdminPanel = { name, price, dataAdded, description };
            await updatePosition(id, updatedAdminPanel, token);
            navigate('/hello'); 
        } catch (err) {
            setError(err.response ? err.response.data : err.message);
        }
    };

    return (
        <div className="container">
            <h2>Update Position</h2>
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
                <button type="submit">Update</button>
            </form>
        </div>
    );
};

export default UpdatePosition;
