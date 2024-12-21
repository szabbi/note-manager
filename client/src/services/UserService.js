import axios from "axios";

const apiUser = axios.create({
	baseURL: "http://localhost:8080",
	headers: {
		"Content-Type": "application/json",
		withCredentials: true,
	},
});

export const register = (user) => {
	return apiUser.post("/auth/registration", user);
};

export const login = (user) => {
	return apiUser.post("/auth/login", user);
};

export const logout = () => {
	return apiUser.post("/logout");
};

export const getUser = () => {
	return apiUser.get("/auth/getUser", {
		headers: {
			Authorization: `Bearer ${sessionStorage.getItem("token")}`,
		},
	});
};
