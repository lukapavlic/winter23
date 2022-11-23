import axios from 'axios';

export const measurementsApi = axios.create({
    baseURL: process.env.REACT_APP_BACKEND_URL,
    timeout: 30000,
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
    },
});