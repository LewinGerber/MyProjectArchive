import React, { useState, useEffect } from 'react';
import { NativeBaseProvider, Box } from 'native-base';
import { StyleSheet, TouchableOpacity} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

import Header from './components/Header';
import StartButton from './components/StartButton';
import BottomNav from './components/BottomNav';

const styles = StyleSheet.create({
  wrapper: {
    flex: 1,
  }, 
  touchArea: {
    position: 'absolute',
    backgroundColor: 'transparent',
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
  },
});

export default function App() {
  // 0: default, 1: waiting, 2: result
  const [mode, setMode] = useState(0);
  const [bgColor, setBgColor] = useState('#1E62FF');
  const [timer, setTimer] = useState();
  const [isWaiting, setIsWaiting] = useState(false);
  const [isDone, setIsDone] = useState(false);
  const [waitTime, setWaitTime] = useState();
  const [result, setResult] = useState("");
  const [highScore, setHighScore] = useState();
  
  useEffect(() => {
    getStoredHighScore();
  }, []);

  const getStoredHighScore = async () => {
    try {
      const value = await AsyncStorage.getItem('highScore')
      if (value !== null) setHighScore(parseFloat(value));
    } catch (e) { }
  }

  const storeHighScore = async (value) => {
    try {
      await AsyncStorage.setItem('highScore', "" + value)
    } catch (e) { console.error("HighScore not stored: " + e) }
  }

  function evaluateHighScore(score) {
    if (score < highScore || highScore == undefined) {
      setHighScore(score);
      storeHighScore(score);
    }
  }

  function changeMode(mode) {
    switch(mode) {
      case 0:
        setMode(0);
        setBgColor('#1E62FF');
        break;
      case 1:
        setMode(1);
        setBgColor('#FF1E5B');
        break;
      case 2:
        setMode(2);
        if (!isWaiting) setBgColor('#0AD871');
        break;
    }
  }

  function startTimer() {
    setIsWaiting(true);
    let randomTime = Math.floor((Math.random() * 4800) + 200);
    setTimer(setTimeout(() => {
      // on timer finished
      setIsWaiting(false)
      changeMode(2);
      setWaitTime(new Date());
    }, randomTime))
  }

  const clickHandler = () => {
    if (mode == 0) {
      changeMode(1);
      startTimer();
    } else if (mode == 1) {
      if (isWaiting) {
        clearInterval(timer);
        setResult("Too soon ...");
        setIsWaiting(false);
        setIsDone(true);
        changeMode(2);
      }
    }
    if (mode == 2) {
      if (!isWaiting && !isDone) {
        let sec = (new Date() - waitTime.getTime()) / 1000;
        setResult(sec);
        evaluateHighScore(sec);
        setIsDone(true);
        console.log(sec);
      }
      if (isDone) {
        changeMode(0);
        setResult("");
        setIsDone(false);
      }
    }
  }

  return (
    <NativeBaseProvider>
      <Box style={{backgroundColor: bgColor}} flex={1}>
        <Header mode={mode} result={result} highScore={highScore}/>
        { mode === 0 && <StartButton /> }
        <TouchableOpacity style={styles.touchArea} onPress={() => clickHandler()} />
        <Box flex={0.5}>
          { (mode === 2 && result !== "Too soon ..." && result != "" && result !== undefined || mode === 0)  && <BottomNav mode={mode} highScore={highScore}/>}
        </Box>
      </Box>
    </NativeBaseProvider>
  );
}
