import React from 'react';
import { StyleSheet, TouchableOpacity, Share } from 'react-native';
import { Box, Heading } from 'native-base';

const styles = StyleSheet.create({
  heading: {
    lineHeight: 60,
    color: 'white',
  },
  customButton: {
    width: 175,
    height: 50,
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
    zIndex: 20,
  },
  customButtonBanner: {
    width: 310,
    height: 50,
    borderRadius: 100,
    backgroundColor: '#033EC5',
    flexDirection: 'row',
    justifyContent: "flex-end",
    alignItems: "center",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 8,
    },
    shadowOpacity: 0.20,
    shadowRadius: 20,
    elevation: 15,
    zIndex: 20,
  },
  bottomNav: {
    position: 'absolute',
    left: 45,
    bottom: 80,
  },
});

export const BottomNav = ({mode, highScore}) => {
    const onShare = async () => {
      try {
        const result = await Share.share({
          message: 'How fast can you react? My high score is: ' + highScore,
        });
        if (result.action === Share.sharedAction) {
          if (result.activityType) {
            // shared with activity type of result.activityType
          } else {
            // shared
          }
        } else if (result.action === Share.dismissedAction) {
          // dismissed
        }
      } catch (error) {
        alert(error.message);
      }
    };

    const ResultVariant = () => (
      <Box style={styles.bottomNav} flex={1.25}>
        <Heading style={[{ opacity: 0.7 }, styles.heading]} bold size="md">SHARE YOUR SCORE</Heading>
        <TouchableOpacity style={[{backgroundColor: '#1E62FF'}, styles.customButton]} onPress={async () => await onShare()}>
          <Heading style={{ color: 'white' }} size="xs">BLUETOOTH</Heading>
        </TouchableOpacity>
      </Box>
    );

    const HomeVariant = ({highScore}) => (
      <Box style={styles.bottomNav} flex={1.25}>
        <Heading style={[{ opacity: 0.7 }, styles.heading]} bold size="md">SHARE YOUR HIGH SCORE</Heading>
        <TouchableOpacity style={[{ backgroundColor: 'white' }, styles.customButtonBanner]} onPress={async () => await onShare()}>
          <Heading style={{ color: 'white', paddingRight: 30}} size="xl">{highScore}</Heading>
          <TouchableOpacity style={[, styles.customButton, { backgroundColor: 'white', width: 150 }]}  onPress={async () => await onShare()}>
            <Heading style={{ color: 'black' }} size="xs">BLUETOOTH</Heading>
          </TouchableOpacity>
        </TouchableOpacity>
      </Box>
    );
  
    return (
      <>
        { mode == 2 ? <ResultVariant /> : <HomeVariant highScore={highScore} /> }
      </>
    );
}

export default BottomNav;