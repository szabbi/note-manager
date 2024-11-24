import axios from "axios";

const apiNote = axios.create({
	baseURL: "http://localhost:8080/api/note",
	headers: {
		"Content-Type": "application/json",
		withCredentials: true,
	},
});

export const add = (user) => {
	return apiNote.post("/add", user);
};

export const getPublicNotes = () => {
	return apiNote.get("/getpublicnotes");
};

export const getUserNotes = () => {
	return apiNote.get("/getbyuser", {
		headers: {
			Authorization: `Bearer ${localStorage.getItem("token")}`,
		},
	});
};
