import React, { useRef, useState } from "react";
import { register } from "../services/UserService";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

const Registration = () => {
	const [name, setName] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const navigator = useNavigate();

	function registerUser(e) {
		e.preventDefault();
		const user = { name, email, password };

		register(user)
			.then((response) => {
				toast.success("Registration successful!");
				console.log(response.data);
				navigator("/");
			})
			.catch((error) => {
				toast.error(error.response.data);
			});
	}

	return (
		<div className="container mt-5">
			<div className="row">
				<div className="card col-md-6 offset-md-3">
					<h2 className="text-center">Registration</h2>
					<div className="card-body">
						<form className="row g-2">
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
							</div>
							<div className="form-group mb-2">
								<label className="form-label">Password</label>
								<input
									type="password"
									placeholder="Enter user password"
									name="password"
									value={password}
									className="form-control"
									onChange={(e) => setPassword(e.target.value)}
									required
								/>
							</div>
							<button type="submit" className="btn btn-success" onClick={registerUser}>
								Submit
							</button>
						</form>
					</div>
				</div>
			</div>
			<ToastContainer />
		</div>
	);
};

export default Registration;
