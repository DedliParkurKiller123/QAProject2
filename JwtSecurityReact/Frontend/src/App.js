import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './component/auth/Register';
import Login from './component/auth/Login';
import AdminPanel from './component/hello/AdminPanel';
import UpdatePosition from './component/hello/UpdatePosition';
import AddPosition from './component/hello/AddPosition';

const App = () => {
    return (
        <Router>
            <div>
                <Routes>
                    <Route path="/register" element={<Register />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/hello" element={<AdminPanel/>} />
                    <Route path="/update/:id" element={<UpdatePosition/>} />
                    <Route path="/add" element={<AddPosition/>} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;