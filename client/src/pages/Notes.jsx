import { getUserNotes } from "../services/NoteService";
import { useState, useEffect, React } from "react";
import { ToastContainer, toast } from "react-toastify";

const Notes = () => {
	const [notes, setNotes] = useState([]);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		getNotes();
	}, []);

	function getNotes() {
		getUserNotes()
			.then((response) => {
				console.log(response.data);

				setNotes(response.data);
				toast.success("Notes fetched successfully!");
			})
			.catch((error) => {
				const errorMessage = error.response?.data?.message;
				toast.error(errorMessage);
			})
			.finally(() => {
				setLoading(false);
			});
	}

	if (loading) return <div>Loading...</div>;

	return (
		<div className="container mt-5">
			<button type="button" className="btn btn-success" onClick={getNotes}>
				Get Notes
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
									<h5 className="card-title">{note.title}</h5>
									<p className="card-text">{note.content}</p>
									<p className="text-muted">
										<small>Created At: {new Date(note.createdAt).toLocaleString()}</small>
									</p>
									<p className="text-muted">
										<small>Created By: {note.createdBy}</small>
									</p>
									<p>
										<strong>Public:</strong> {note.publicNote ? "Yes" : "No"}
									</p>
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
