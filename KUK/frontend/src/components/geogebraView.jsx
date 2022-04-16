import { useEffect, useState } from 'react';
import { Button, TextField, Paper, FormControl, MenuItem, Select } from '@material-ui/core';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import RadioButtonCheckedIcon from '@material-ui/icons/RadioButtonChecked';
import AdbIcon from '@material-ui/icons/Adb';
import AddIcon from '@material-ui/icons/Add';
import DoneAllIcon from '@material-ui/icons/DoneAll';
import GeoGebra from 'react-geogebra';
import axios from "axios";
import '../App.css';

export const GeoGebraView = (props) => {
    const [sessionId, setSessionId] = useState(1);
    const [points, setPoints] = useState([]);
    const [pointCounter, setPointCounter] = useState(0);
    const [pointSize, setPointSize] = useState(0);
    const [calculationAlgorithm, setCalculationAlgorithm] = useState('pdc');
    //const [points, setPoints] = useState(props.points);
    //const [hightlightedPoints, setHighlightedPoints] = useState(props.hightlightedPoints);
    //<GeoGebra height="100%" width="100%" showToolBar />

    const generatePoints = async () => {
        try {
            const result = await axios.post('http://localhost:8080/generatePoints', pointSize);
            setPoints([]);
            setPoints(result.data.points);
            console.log('points size:' + points.length);
            displayPoints();
        } catch (e) { console.error(e) }
    }

    const calculateResult = async () => {
        try {
            const result = await axios.post('http://localhost:8080/' + calculationAlgorithm, sessionId);
        } catch (e) { console.error(e) }
    }

    useEffect(() => {
    }, []);

    function displayPoints() {
        console.log("1");
        points.map(point => {
            console.log("2");
            placePointOnCanvas(point.posX, point.posY)
        });
    }

    function placePointOnCanvas(x, y) {
        const canvas = window.canvas;
        var ctx = canvas.getContext("2d");
        console.log("draw");
        ctx.moveTo(x + 150, y + 150);
        ctx.lineTo(10, 10);
        ctx.stroke();
    }

    return (
        <div id="geogebra-view">
            <h2 id="secondary-effect">Hier selbst test!</h2>
            <div id="geogebra-manual">
                <p>
                    Bei dieser kleinen Demo gibt es die Möglichkeit diese 2 Algorithmen selbst auszuprobieren.
                    Auf dem roten Menü unten muss zuerst die gewünschte Anzahl Punkte angegeben werden. Anschliessend kann
                    aus dem Dropdown-Menü einer der 2 Algorithem ausgewählt werden.
                </p>
            </div>

            <div id="geogebra-content">

                <div id="geogebra-interface">
                    <canvas id="canvas"></canvas>
                </div>

                <div id="geogebra-menu">
                    <div id="menu-background"></div>
                    <Paper className="menu-element">
                        <div>
                            <h3 id="controller-title">
                                <span><SportsEsportsIcon /></span>
                                <span>Algorithmus</span>
                                <span>Kontroller</span>
                            </h3>
                        </div>
                    </Paper>
                    <Paper className="menu-element">
                        <div>
                            <p className="menu-subtitle"><RadioButtonCheckedIcon /><span>Punkte setzen</span></p>
                            <form id="form-1-points" className="menu-form" onSubmit={(e) => e.preventDefault()}>
                                <TextField variant="outlined" type="number" label="Anzahl Punkte" min="0" max="100" value={pointSize} onChange={(e) => setPointSize(e.target.value)} />
                                <Button type="submit" variant="contained" color="secondary" onClick={() => generatePoints()}><AddIcon /> Generieren</Button>
                            </form>
                        </div>
                    </Paper>
                    <Paper className="menu-element">
                        <div>
                            <p className="menu-subtitle"><AdbIcon /><span>Algorithmus wählen</span></p>
                            <form id="form-2-algorithm" className="menu-form" onSubmit={(e) => e.preventDefault()}>
                                <FormControl variant="outlined" id="form-2-algorithm-select">
                                    <Select value={calculationAlgorithm} onChange={(e) => { setCalculationAlgorithm(e.target.value) }}>
                                        <MenuItem id="algo-sec-1" value={'pdc'}>13 Punkte</MenuItem>
                                        <MenuItem id="algo-sec-2" value={'sdc'}>Sequentiell</MenuItem>
                                    </Select>
                                </FormControl>
                            </form>
                        </div>
                    </Paper>
                    <Button variant="contained" color="secondary" onClick={() => calculateResult()}><DoneAllIcon />Berechnen</Button>
                </div>
            </div>
        </div>
    );
}