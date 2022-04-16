import logo from '../logo.svg';
import './react-styles.css';

export const ReactPage = () => {
    return (
        <div className="react-app">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    Animations in WebApplications
            </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Explore
            </a>
            </header>
        </div>
    );
}

export default ReactPage;