import React, { useState } from 'react';
import { login } from '../../services/serviceAuth/authService';
import './Login.css'; // Додано для імпорту стилів

const Login = () => {
    const [phoneNumber, setPhoneNumber] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({}); 

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const loginData = {
                phoneNumber,
                password,
            };
            const response = await login(loginData);

            localStorage.setItem('token', response.data.jwt);
            
            alert('Login successful!');
            setPhoneNumber('');
            setPassword('');
            setErrors({}); 

        } catch (error) {
            const validationErrors = error.response.data.errors;
            console.log(validationErrors);
            if (error.response && error.response.status === 400) {
                setErrors({ auth: validationErrors.auth });
            } else {
                alert('Login failed: ' + error.message);
            }
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <input 
                    type="text" 
                    placeholder="Phone Number" 
                    value={phoneNumber} 
                    onChange={(e) => setPhoneNumber(e.target.value)} 
                />
                <input 
                    type="password" 
                    placeholder="Password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                />
                {errors.auth && <p className="error">{errors.auth}</p>}
                
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;
