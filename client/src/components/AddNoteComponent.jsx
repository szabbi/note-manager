import { React, useState } from "react";
import { createNote } from "../services/NoteService";
import { useNavigate } from "react-router-dom";

const AddNoteComponent = () => {
	const [title, setTitle] = useState("");
	const [content, setContent] = useState("");
	const [publicNote, setPublicNote] = useState(false);
	const navigator = useNavigate();

	const handleSubmit = async (e) => {
		e.preventDefault();
		const newNote = {
			title,
			content,
			publicNote,
		};

		try {
			await createNote(newNote);
			navigator("/");
		} catch (error) {
			console.log(error);
		}
	};
	return (
		<div className="container mt-5">
			<div className="row justify-content-center">
				<div className="col-md-6">
					<div className="card shadow-lg">
						<div className="card-header bg-primary text-white text-center">
							<h4>Create a new note</h4>
						</div>
						<div className="card-body">
							<form onSubmit={(e) => handleSubmit(e)}>
								<div className="mb-3">
									<label htmlFor="title" className="form-label">
										Title
									</label>
									<input
										type="text"
										id="title"
										className="form-control"
										placeholder="Enter note title"
										value={title}
										onChange={(e) => setTitle(e.target.value)}
										required
									/>
								</div>
								<div className="mb-3">
									<label htmlFor="content" className="form-label">
										Content
									</label>
									<textarea
										id="content"
										className="form-control"
										rows="5"
										placeholder="Enter note content"
										value={content}
										onChange={(e) => setContent(e.target.value)}
										required></textarea>
								</div>
								<div className="form-check mb-3">
									<input
										type="checkbox"
										id="isPublic"
										className="form-check-input"
										checked={publicNote}
										onChange={(e) => setPublicNote(e.target.checked)}
									/>
									<label htmlFor="isPublic" className="form-check-label">
										Public Note
									</label>
								</div>
								<div className="d-flex justify-content-center">
									<button type="submit" className="btn btn-success">
										Create note
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default AddNoteComponent;
