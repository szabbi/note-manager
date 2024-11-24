import { getCurrentUserNotes, updateNote, deleteNote } from "../services/NoteService";
import { useState, useEffect, React } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const Notes = () => {
	const [notes, setNotes] = useState([]);
	const navigator = useNavigate();

	useEffect(() => {
		handleGetNotes();
	}, []);

	const handleGetNotes = async () => {
		getCurrentUserNotes()
			.then((response) => {
				setNotes(response.data);
				toast.success("Notes fetched successfully!");
			})
			.catch((error) => {
				const errorMessage = error.response?.data?.message;
				toast.error(errorMessage);
			});
	};

	function handleAddNote() {
		navigator("/add-note");
	}

	const handleUpdateNote = (id) => {};

	const handleDeleteNote = async (id) => {
		await deleteNote(id);
		handleGetNotes();
	};

	return (
		<div className="container mt-5">
			<button type="button" className="btn btn-success" onClick={() => handleAddNote()}>
				Add note
			</button>
			<h1 className="text-center mb-4">Your Notes</h1>
			{notes.length === 0 ? (
				<div className="alert alert-info text-center">No notes found. Start creating some!</div>
			) : (
				<div className="row">
					{notes.map((note) => (
						<div key={note.id} className="col-md-4 mb-4">
							<div className="card">
								<div className="card-body">
									<h3 className="card-title text-center">{note.title}</h3>
									<hr className="mx-1"></hr>
									<p className="card-text mx-2">{note.content}</p>
									<hr className="mx-1"></hr>
									<div className="d-flex justify-content-between align-items-start">
										<div className="d-flex justify-content-between">
											<div className="d-flex flex-column">
												<button className="btn btn-primary btn-sm m-1" onClick={() => handleUpdateNote(note.id)}>
													<i className="bi bi-pencil"></i>
												</button>
												<button className="btn btn-danger btn-sm m-1" onClick={() => handleDeleteNote(note.id)}>
													<i className="bi bi-trash"></i>
												</button>
											</div>
										</div>
										<div className=" d-flex flex-column align-items-end">
											<p className="m-0">{note.createdBy}</p>
											<p className="text-muted m-0">
												<small>
													{new Date(note.createdAt).toLocaleString("hu-HU", {
														year: "numeric",
														month: "numeric",
														day: "numeric",
														hour: "2-digit",
														minute: "2-digit",
													})}
												</small>
											</p>
											<p>
												<strong>{note.publicNote ? "Public" : "Private"}</strong>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					))}
				</div>
			)}
			<ToastContainer />
		</div>
	);
};

export default Notes;
