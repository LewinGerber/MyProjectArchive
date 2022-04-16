import React, { useRef, useEffect } from 'react';
import { Box, Heading } from 'native-base';
import { StyleSheet, Animated, TouchableOpacity } from 'react-native';

const styles = StyleSheet.create({
    startButton: {
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: 'transparent',
    },
    circleButton: {
        justifyContent: "center",
        width: 200,
        height: 200,
        alignItems: "center",
        backgroundColor: 'white',
        borderRadius: 100000,
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 8,
        },
        shadowOpacity: 0.25,
        shadowRadius: 20,
        elevation: 15,
      },
      smallCircleButton: {
        width: 80,
        height: 80,
        backgroundColor: '#1E62FF',
        borderRadius: 100,
        justifyContent: "center",
        alignItems: "center",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 8,
        },
        shadowOpacity: 0.25,
        shadowRadius: 20,
        elevation: 15,
      },
});

export const StartButton = () => {
    const sizeAnim = useRef(new Animated.Value(0.85)).current
  
    function increaseCircleSize() {
      Animated.timing(
        sizeAnim,
        { toValue: 1.25, duration: 800, useNativeDriver: true }
      ).start(decreaseCircleSize);
    }
  
    function decreaseCircleSize() {
      Animated.timing(
        sizeAnim,
        { toValue: 0.85, duration: 800, useNativeDriver: true }
      ).start(increaseCircleSize);
    }
  
    useEffect(() => {increaseCircleSize()}, []);
  
    return (
      <Box style={styles.startButton} flex={1.25}>
        <Animated.View style={[{transform: [{ scale: sizeAnim }]}, styles.circleButton]}>
          <TouchableOpacity style={styles.smallCircleButton}>
            <Heading style={{ color: 'white' }} size="lg">TAP</Heading>
          </TouchableOpacity>
        </Animated.View>
      </Box>
    );
  };

  export default StartButton;