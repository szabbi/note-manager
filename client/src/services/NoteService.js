import axios from "axios";

const apiNote = axios.create({
	baseURL: "http://localhost:8080/api/note",
	headers: {
		"Content-Type": "application/json",
		withCredentials: true,
		Authorization: `Bearer ${sessionStorage.getItem("token")}`,
	},
});

export const createNote = (note) => {
	return apiNote.post("/add", note);
};

export const getPublicNotes = () => {
	return apiNote.get("/getpublicnotes");
};

export const getCurrentUserNotes = () => {
	return apiNote.get("/getbyuser");
};

export const updateNote = (note) => {
	return apiNote.put("/update", note);
};

export const deleteNote = (id) => {
	return apiNote.delete(`/delete?id=${id}`);
};
