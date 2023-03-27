import { Route, Navigate } from 'react-router-dom';
export { PrivateRoute };

function PrivateRoute({ component: Component, ...rest }) {
    const auth = sessionStorage.getItem("loggedIn") === "true";
    return (
        <Route {...rest} render={props => {
            if (!auth) {
                return <Navigate to="/"/>
            }
            return <Component {...props} />
        }} />
    );
}