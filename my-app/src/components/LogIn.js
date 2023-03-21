import { useState, useEffect } from "react";
import { Container, Row, Col } from "react-bootstrap";
import 'animate.css';
import login from '../assets/video/login.mp4'

import { Link } from "react-router-dom";
export const LogIn = () => {
    const [loopNum, setLoopNum] = useState(0);
    const [isDeleting, setIsDeleting] = useState(false);
    const [text, setText] = useState('');
    const [delta, setDelta] = useState(300 - Math.random() * 100);
    const [index, setIndex] = useState(1);
    const toRotate = [ "przy", "psyjaciela" ];
    const period = 1000;

    useEffect(() => {
        let ticker = setInterval(() => {
            tick();
        }, delta);

        return () => { clearInterval(ticker) };
    }, [text])

    const tick = () => {
        let i = loopNum % toRotate.length;
        let fullText = toRotate[i];
        let updatedText = isDeleting ? fullText.substring(0, text.length - 1) : fullText.substring(0, text.length + 1);

        setText(updatedText);

        if (isDeleting) {
            setDelta(prevDelta => prevDelta / 2);
        }

        if (!isDeleting && updatedText === fullText) {
            setIsDeleting(true);
            setIndex(prevIndex => prevIndex - 1);
            setDelta(period);
        } else if (isDeleting && updatedText === '') {
            setIsDeleting(false);
            setLoopNum(loopNum + 1);
            setIndex(1);
            setDelta(500);
        } else {
            setIndex(prevIndex => prevIndex + 1);
        }
    }

    return (
        <section className="login" id="login">
            <Container>
                <div className='login'>
                    <video src={login} autoPlay loop muted /> </div>
                <Row className="aligh-items-center">
                    <Col xs={12} md={6} xl={7}>
                        <div className="Auth-form-content">
                        <h3 className="Auth-form-title">ZALOGUJ SIĘ</h3>
                        <div className="form-group mt-3">
                            <input
                                type="email"
                                className="form-control mt-1"
                                placeholder="twój mail"
                                />
                            <p><label>E-MAIL</label></p>
                        </div>
                        <div className="form-group mt-3">
                            <input
                                type="password"
                                className="form-control mt-1"
                                placeholder="twoje hasło"
                            />
                            <p><label>HASŁO</label></p>
                            <h4 className="forgot-password text-right mt-2">
                                Zapomniałeś/aś <a href="#">hasła?</a>
                            </h4>
                        </div>
                        <div className="d-grid gap-2 mt-3">
                            <button type="submit" className="btn btn-primary">
                                <Link to="/logged">ZALOGUJ</Link>
                            </button>
                        </div>
                    </div>
                    <h2><Link to="/signup">ZAŁÓŻ KONTO</Link></h2>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default LogIn