import axios from "axios";

const apiNote = axios.create({
	baseURL: "http://localhost:8080/api/note",
	headers: {
		"Content-Type": "application/json",
		withCredentials: true,
		Authorization: `Bearer ${localStorage.getItem("token")}`,
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

export const updateNote = (id, note) => {
	return apiNote.put("/update");
};

export const deleteNote = (id) => {
	return apiNote.delete(`/delete?id=${id}`);
};
