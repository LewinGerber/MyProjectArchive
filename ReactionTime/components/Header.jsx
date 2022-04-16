import React, { useEffect, useState } from 'react';
import { Box, Heading, Text } from 'native-base';
import { StyleSheet, Image } from 'react-native';

const styles = StyleSheet.create({
    heading: {
        lineHeight: 60,
        color: 'white',
    },
    container: {
        paddingRight: 40,
        paddingLeft: 40,
    },
    icon: {
      height: 50, 
      width: 50, 
      resizeMode : 'cover',
      marginTop: 50,
      marginBottom: 40,
    },
    text: {
      color: 'white',
      marginTop: 10
    },
});

const ScoreView = ({result}) => (
  <>
    <Heading style={[{ opacity: 0.7 }, styles.heading]} bold size="xl">Score</Heading>
    <Box style={{ flexDirection: "row", justifyContent: "flex-start", alignItems: "baseline" }}>
      <Heading style={styles.heading} bold size="4xl">{result}</Heading>
      <Heading style={[{ opacity: 0.8, marginLeft: 10 }, styles.heading]} bold size="md" >seconds</Heading>
    </Box>
  </>
);

const HomePage = ({highScore}) => (
  <>
    <Heading style={styles.heading} bold size="3xl">Reaction</Heading>
    <Heading style={styles.heading} bold size="3xl">Time</Heading>
    <Text style={styles.text}>
      Tap your screen when Ready and wait for the
      screen to turn green.
    </Text>
    
  </>
);

const ResultPage = (props) => (
  <Box style={styles.container} flex={1}>
    <Image
      source={require('../assets/clock-64.png')}
      style={styles.icon}
    />
    { props.children }
  </Box>
);

const TooSoonView = ({result}) => (
  <>
    <Heading style={[{ opacity: 0.7 }, styles.heading]} bold size="xl">Oops!</Heading>
    <Heading style={styles.heading} bold size="3xl">{result}</Heading>
  </>
)

export const Header = ({mode, result, highScore}) => {
  const [resultValid, setResultValid] = useState(result !== undefined && result !== "")

  useEffect(() => {
    setResultValid(result !== undefined && result !== "")
  }, [result]);
  return (
    <>
        <ResultPage>
          {mode === 0 ? <HomePage highScore={highScore}/> : null}
          {mode === 1 ? <Heading style={styles.heading} bold size="3xl">Wait ...</Heading> : null}
          {mode === 2 && resultValid && result !== "Too soon ..." ? <ScoreView result={result} /> : null}
          {mode === 2 && resultValid && result === "Too soon ..." ? <TooSoonView result={result} /> : null}
        </ResultPage>
    </>
  );
}

export default Header;