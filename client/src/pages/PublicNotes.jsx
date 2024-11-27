import { getPublicNotes } from "../services/NoteService";
import { useState, useEffect, React } from "react";

const PublicNotes = () => {
	const [notes, setNotes] = useState([]);

	useEffect(() => {
		getPublicUserNotes();
	}, []);

	const getPublicUserNotes = async () => {
		try {
			const response = await getPublicNotes();
			setNotes(response.data);
		} catch (error) {
			console.log(error);
		}
	};

	return (
		<div className="container mt-5">
			<h1 className="text-center mb-4">Public Notes</h1>
			{notes.length === 0 ? (
				<div className="alert alert-info text-center">No public notes found.</div>
			) : (
				<div className="row">
					{notes.map((note) => (
						<div key={note.id} className="col-md-3 mb-4">
							<div className="card shadow">
								<div className="card-body flex-column">
									<h3 className="card-title text-center">{note.title}</h3>
									<hr className="mx-1"></hr>
									<p className="card-text mx-2">{note.content}</p>
									<hr className="mx-1"></hr>
									<div className="d-flex flex-column align-items-end">
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
									</div>
								</div>
							</div>
						</div>
					))}
				</div>
			)}
		</div>
	);
};

export default PublicNotes;
