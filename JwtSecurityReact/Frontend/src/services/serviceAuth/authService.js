import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/'; 

export const register = async (registerData) => {
    return await axios.post(`${API_URL}register`, registerData);
};

export const login = async (loginData) => {
    return await axios.post(`${API_URL}authentication`, loginData);
};
