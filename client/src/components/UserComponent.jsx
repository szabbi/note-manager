import React, { useRef, useState } from "react";
import { addUser } from "../services/UserService";
import { useNavigate } from "react-router-dom";

const UserComponent = () => {
	const [name, setName] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const navigator = useNavigate();
	const formRef = useRef(null);

	function saveUser(e) {
		e.preventDefault();
		const user = { name, email, password };
		const form = formRef.current;

		if (form.checkValidity() == false) {
			form.classList.add("was-validated");
		} else {
			alert("Form submitted successfully!");
			addUser(user).then((response) => {
				console.log(response.data);
				navigator("/users");
			});
		}
	}

	return (
		<div className="container mt-5">
			<div className="row">
				<div className="card col-md-6 offset-md-3">
					<h2 className="text-center">Add User</h2>
					<div className="card-body">
						<form
							className="row g-2 needs-validation"
							noValidate
							ref={formRef}
						>
							<div className="form-group mb-2">
								<label className="form-label">Name</label>
								<input
									type="text"
									placeholder="Enter first name"
									name="name"
									value={name}
									className="form-control"
									onChange={(e) => setName(e.target.value)}
									required
								/>
								<div className="invalid-feedback">
									Please enter your name!
								</div>
							</div>
							<div className="form-group mb-2">
								<label className="form-label">Email</label>
								<input
									type="email"
									placeholder="Enter user email"
									name="email"
									value={email}
									className="form-control"
									onChange={(e) => setEmail(e.target.value)}
									required
								/>
								<div className="invalid-feedback">
									Please enter a valid email address.
								</div>
							</div>
							<div className="form-group mb-2">
								<label className="form-label">Password</label>
								<input
									type="password"
									placeholder="Enter user password"
									name="password"
									value={password}
									className="form-control"
									onChange={(e) =>
										setPassword(e.target.value)
									}
									required
								/>
								<div className="invalid-feedback">
									Please enter your password!
								</div>
							</div>
							<button
								type="submit"
								className="btn btn-success"
								onClick={saveUser}
							>
								Submit
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	);
};

export default UserComponent;
