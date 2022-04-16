//import { useEffect, useState } from 'react';
import { Button, Paper } from '@material-ui/core';
import ExploreIcon from '@material-ui/icons/Explore';
import '../App.css';

export function TitleView() {
    return (
        <div className="view101" id="title-screen">
            <div id="kbw-logo"></div>
            <div id="t-s-content">
                <h1 id="secondary-effect">KUK</h1>
                <p><span className="text-bold">K</span>leinster <span className="text-bold">U</span>mschliessender <span className="text-bold">K</span>reis</p>
                <div id="explore-button">
                    <Button variant="contained" color="secondary" href="#content">
                        <ExploreIcon className="text-icon" />
                        <p>Entdecken</p>
                    </Button>
                </div>
            </div>
            <div id="gradient"></div>
        </div>
    );
}

export function Content() {
    return (
        <div id="content-view">
            <div id="title-text">
                <h2 id="secondary-effect">Was ist der kleinste umschliessende Kreis?</h2>
                Der kleinste umschliessende Kreis ist ein eindeutiger Kreis um eine gegebene Anzahl von Punkten.
                Obowhl das visuelle Vorstellen eines solchen Kreises keine grosse Schwierigkeit darstellt, ist dies
                ein sehr berühmtes Problem in de algorithmischen Geometrie.
            </div>
            <div id="title-text">
                <h2 id="secondary-effect">Was gibt es für Lösungen?</h2>
                <p>
                    Auf der <a href="https://en.wikipedia.org/wiki/Smallest-circle_problem">Wikipedia Seite</a> werden verschiedenste
                    Möglichkeiten präsentiert um dieses Problem zu lösen. Der berühmteste Ansatz ist der von Dr. Prof. Emo Welzl welcher
                    eine Idee für einen Algorithmus entwickelt hat, welcher durch trategisches Raten zu seinen Resultaten kommt.</p>
                <p className="m-t-1">
                    Ein sehr anderer Ansatz wird auf der Webseite <a href="https://www.nayuki.io/page/smallest-enclosing-circle">Nayuki.io</a>
                    präsentiert. Der dortige Algorithmus konzentriert sich auf einen Seuquentiellen Ablauf wie es in diesem <a href="https://www.nayuki.io/res/smallest-enclosing-circle/computational-geometry-lecture-6.pdf">Dokument</a> erklärt wird.
                    Die Webseite von Nayuki ist durchaus wert besucht zu werden. Er selbst hat eine interaktives Element eingebaut um
                    dieses Problem zu visualisieren.
                </p>
            </div>
            <div id="content-algorithm">
                <Paper className="algo-view">
                    <h1 className="algo-title">13 Punkte</h1>
                    <hr />
                    <div className="algo-text">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adip\
                            Lorem ipsum dolor sit amet, consectetur adip\
                            Lorem ipsum dolor sit amet, consectetur adip\
                        </p>
                    </div>
                </Paper>
                <Paper className="algo-view">
                    <h1 className="algo-title">Sequentiell</h1>
                    <hr />
                    <div className="algo-text">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adip\
                            Lorem ipsum dolor sit amet, consectetur adip\
                            Lorem ipsum dolor sit amet, consectetur adip\
                        </p>
                    </div>
                </Paper>
            </div>
        </div>
    );
}

export function FooterInfo() {
    return (
        <div id="footer-info">
            <div className="footer-element">
                <h1 id="secondary-effect">KUK</h1>
            </div>
            <div className="footer-element">
                <p className="footer-text">Created by Anissa, Mischa & Lewin</p>
                <p className="footer-text">©Copyright, All rights reserved, 2020-2021</p>
            </div>
        </div>
    )
}