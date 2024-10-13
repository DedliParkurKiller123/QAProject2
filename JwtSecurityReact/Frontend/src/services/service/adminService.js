import axios from 'axios';

const API_URL = 'http://localhost:8080/api/adminPanel'; 

export const getAllPositions = async (token) => {
    return await axios.get(`${API_URL}/getAllPosition`, {
        headers: {
            'Authorization': `Bearer ${token}`, 
        },
    });
};

export const getPositionById = async (token) => {
    return await axios.get(`${API_URL}/getPositionById`, {
        headers: {
            'Authorization': `Bearer ${token}`, 
        },
    });
};

export const newAddPosition = async (newAddPosition,token) => {
    return await axios.post(`${API_URL}/newAddPosition`, newAddPosition, {
        headers: {
            'Authorization': `Bearer ${token}`, 
        },
    });
};

export const deletePosition = async (id, token) => {
    return await axios.delete(`${API_URL}/deletePosition/${id}`, {
        headers: {
            'Authorization': `Bearer ${token}`, 
        },
    });
};

export const updatePosition = async (id, adminPanel, token) => {
    return await axios.put(`${API_URL}/updatePosition/${id}`, adminPanel, {
        headers: {
            'Authorization': `Bearer ${token}`, 
        },
    });
};
