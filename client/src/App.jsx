import "./App.css";
import HeaderComponent from "./components/HeaderComponent";
import ListUserCompontent from "./components/ListUserCompontent";
import { BrowserRouter, Routes, Route, HashRouter } from "react-router-dom";
import UserComponent from "./components/UserComponent";

function App() {
	return (
		<>
			<HashRouter>
				<HeaderComponent />
				<Routes>
					<Route path="/" element={<ListUserCompontent />}></Route>
					<Route
						path="/users"
						element={<ListUserCompontent />}
					></Route>
					<Route path="/add-user" element={<UserComponent />}></Route>
				</Routes>
			</HashRouter>
		</>
	);
}

export default App;
