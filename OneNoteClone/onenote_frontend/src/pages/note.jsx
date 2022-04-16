import React, { useEffect, useState } from 'react';
import NavButton from '../components/navButton.jsx';
import { Rnd } from "react-rnd";
import { HiOutlineHome } from 'react-icons/hi';
import { Button, ButtonGroup } from 'react-bootstrap';
import axios from 'axios';

import { FiSave, FiDelete } from 'react-icons/fi';
import Canvas from '../components/canvas';
import '../assets/styles/note.css';

export const Note = () => {
    
    const [dataPoints, setDataPoints] = useState([]);
    const [text, setText] = useState("");
    const [contentId, setContentId] = useState(0);

    let defaultPosition = {
        x: window.innerWidth / 2 - 150, 
        y: window.innerHeight / 2 - 125,
        width: 300,
        height: 250 
    }

    const handleClear = () => {
        axios.delete('/api/content/' + contentId)
        .then((res) => {
            
        })
        .catch((err) => { console.log(err);});
    }

    const handleSave = () => {
        axios.post('/api/content', { "id": contentId, "text": text })
        .then((res) => {

        })
        .catch((err) => { console.log(err);});
    }

    useEffect(() => {
        axios.get('/api/content', {headers: { Authorization: localStorage.getItem("ON_TOKEN")}}
        ).then((res) => {
            setContentId(res.data.id);
            console.log("ID: " + res.data.id);
        })
        .catch((err) => { console.log(err);});
    }, []);

    return (
        <div>
            <NavButton route="/">
                <HiOutlineHome className="icon" />
            </NavButton>
            <div className="note-btn-grp">
                <ButtonGroup>
                    <Button variant="info" className="note-option-btn" onClick={handleSave}>
                        <FiSave style={{paddingBottom: '0.15rem', marginRight: '0.25rem'}}/>SAVE
                    </Button>
                    <Button variant="info" className="note-option-btn" onClick={handleClear}>
                        <FiDelete style={{paddingBottom: '0.15rem', marginRight: '0.25rem'}}/>CLEAR
                    </Button>
                </ButtonGroup>
            </div>
            <div className="content-container">
                <div id="drawing-area">
                    <Rnd default={defaultPosition} id="rnd"> <textarea id="textarea" value={text} onChange={(e) => setText(e.target.value)} /> </Rnd>
                    <Canvas setDataPoints={setDataPoints} dataPoints={dataPoints} />
                </div>
            </div>
        </div>
    );
}

export default Note;