import React, { useEffect, useState } from "react";
import { getAllUser } from "../services/UserService";
import { useNavigate } from "react-router-dom";

const ListUserCompontent = () => {
	const [users, setUsers] = useState([]);
	const navigator = useNavigate();

	useEffect(() => {
		getAllUser()
			.then((response) => {
				setUsers(response.data);
			})
			.catch((error) => {
				console.log(error);
			});
	}, []);

	function addUser() {
		navigator("/add-user");
	}
	return (
		<div className="container">
			<h1>List of users</h1>
			<button type="button" className="btn btn-primary" onClick={addUser}>
				Add
			</button>
			<table className="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
					</tr>
				</thead>
				<tbody>
					{users.map((user) => (
						<tr key={user.id}>
							<td>{user.id}</td>
							<td>{user.name}</td>
							<td>{user.email}</td>
							<td>{user.password}</td>
						</tr>
					))}
				</tbody>
			</table>
		</div>
	);
};

export default ListUserCompontent;
