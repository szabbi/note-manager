import { getCurrentUserNotes, updateNote, deleteNote } from "../services/NoteService";
import { useState, useEffect, React, useContext } from "react";
import { useNavigate } from "react-router-dom";

const PersonalNotes = () => {
	const [notes, setNotes] = useState([]);
	const [editingNote, setEditingNote] = useState(null);
	const [editingNoteId, setEditingNoteId] = useState("");
	const [updatedTitle, setUpdatedTitle] = useState("");
	const [updatedContent, setUpdatedContent] = useState("");
	const [updatedPublicNote, setUpdatedPublicNote] = useState("");
	const navigator = useNavigate();

	useEffect(() => {
		handleGetNotes();
	}, []);

	const handleGetNotes = async () => {
		try {
			const respone = await getCurrentUserNotes();
			setNotes(respone.data);
		} catch (error) {
			console.log(error);
		}
	};

	function handleAddNote() {
		navigator("/add-note");
	}

	const handleUpdateNote = (note) => {
		setEditingNote(note);
		setEditingNoteId(note.id);
		setUpdatedTitle(note.title);
		setUpdatedContent(note.content);
		setUpdatedPublicNote(note.publicNote);
	};

	const handleDeleteNote = async (id) => {
		await deleteNote(id);
		await handleGetNotes();
	};

	const handleSubmitUpdate = async (e) => {
		e.preventDefault();
		const updatedNote = {
			id: editingNoteId,
			title: updatedTitle,
			content: updatedContent,
			publicNote: updatedPublicNote,
		};

		await updateNote(updatedNote);
		setEditingNote(null);
		await handleGetNotes();
	};

	return (
		<div className="container mt-5">
			<div className="d-flex align-items-center justify-content-center position-relative mb-4">
				<button type="button" className="btn btn-success position-absolute start-0" onClick={(e) => handleAddNote(e)}>
					Add note
				</button>
				<h1 className=" m-0">Your Notes</h1>
			</div>
			{notes.length === 0 ? (
				<div className="alert alert-info text-center">No notes found. Start creating some!</div>
			) : (
				<div className="row">
					{notes.map((note) => (
						<div key={note.id} className="col-md-4 mb-4">
							<div className="card shadow-sm rounded-2">
								<div className="card-body">
									<h3 className="card-title text-center">{note.title}</h3>
									<hr className="mx-1"></hr>
									<p className="card-text mx-2">{note.content}</p>
									<hr className="mx-1"></hr>
									<div className="d-flex justify-content-between align-items-start">
										<div className="d-flex justify-content-between">
											<div className="d-flex flex-column">
												<button
													type="button"
													className="btn btn-primary fw-bold m-1"
													onClick={() => handleUpdateNote(note)}>
													<i className="bi bi-pencil"></i>
												</button>
												<button
													type="button"
													className="btn btn-danger fw-bold m-1"
													onClick={() => handleDeleteNote(note.id)}>
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
			{editingNote && (
				<>
					<div className="modal-backdrop fade show" style={{ backgroundColor: "rgba(0, 0, 0, 0.5)" }}></div>
					<div
						className="modal fade show"
						tabIndex="-1"
						aria-labelledby="editNoteModalLabel"
						aria-hidden="true"
						style={{ display: "block" }}>
						<div className="modal-dialog modal-dialog-centered">
							<div className="modal-content">
								<div className="modal-header">
									<h5 className="modal-title" id="editNoteModalLabel">
										Edit Note
									</h5>
									<button
										type="button"
										className="btn-close"
										data-bs-dismiss="modal"
										aria-label="Close"
										onClick={() => setEditingNote(null)}></button>
								</div>
								<div className="modal-body">
									<form onSubmit={(e) => handleSubmitUpdate(e)}>
										<div className="mb-3">
											<label htmlFor="noteTitle" className="form-label">
												Title
											</label>
											<input
												type="text"
												className="form-control"
												id="noteTitle"
												value={updatedTitle}
												onChange={(e) => setUpdatedTitle(e.target.value)}
											/>
										</div>
										<div className="mb-3">
											<label htmlFor="noteContent" className="form-label">
												Content
											</label>
											<textarea
												className="form-control"
												id="noteContent"
												value={updatedContent}
												onChange={(e) => setUpdatedContent(e.target.value)}
											/>
										</div>
										<div className="form-check mb-3">
											<input
												type="checkbox"
												id="isPublic"
												className="form-check-input"
												checked={updatedPublicNote}
												onChange={(e) => setUpdatedPublicNote(e.target.checked)}
											/>
											<label htmlFor="isPublic" className="form-check-label">
												Public Note
											</label>
										</div>
										<div className="d-flex justify-content-between">
											<button type="submit" className="btn btn-success">
												Update Note
											</button>
											<button
												type="button"
												className="btn btn-secondary"
												onClick={() => setEditingNote(null)} // Close the edit form without saving
											>
												Cancel
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</>
			)}
		</div>
	);
};

export default PersonalNotes;
