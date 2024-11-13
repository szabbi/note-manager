import axios from "axios";

const api = axios.create({
	baseURL: "http://localhost:8080/api/user",
	headers: {
		"Content-Type": "application/json",
	},
});

export const addUser = (user) => {
	return api.post("/add", user);
};

export const getAllUser = () => {
	return api.get("/getall");
};

export const getUserById = (id) => {
	api.get("/getbyid?id=${id}");
};

export const updateUser = (user) => {
	api.put("/update", user);
};

export const deleteUser = (id) => {
	api.delete("/delete?id=${id}", user);
};
