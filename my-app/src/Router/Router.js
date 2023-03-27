import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import HomePage from "../HomePage/HomePage";

const RouterReact = () => (
	<Router>
		<Routes>
			<Route path="/" element={<HomePage />} />
		</Routes>
	</Router>
)

export default RouterReact