import { React, useState } from 'react';
import PropTypes from 'prop-types';
import SwipeableViews from 'react-swipeable-views';
import { AppBar, Tabs, Tab, makeStyles, createMuiTheme, ThemeProvider } from '@material-ui/core';
import './App.css';
import ReactPage from './pages/react';
import SvgPage from './pages/svg';
import CanvasPage from './pages/canvas';
import GsapPage from './pages/gsap';
import P5Page from './pages/p5';

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      {value === index && (
        <div className="tab-content">
          {children}
        </div>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    'aria-controls': `full-width-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100vw',
    position: 'fixed',
    height: '100vh',
  },
}));

function App() {
  const classes = useStyles();
  const theme = createMuiTheme ({
    palette: {
      type: 'dark',
      primary: {
        main: '#00ff00'
      },
    }
  });

  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleChangeIndex = (index) => {
    setValue(index);
  };

  return (
    <ThemeProvider theme={theme}>
      <div className="App">
        <div className={classes.root}>
          <AppBar position="static" color="default">
            <Tabs
              value={value}
              onChange={handleChange}
              indicatorColor="primary"
              textColor="primary"
              variant="fullWidth"
              className="tabs-wrapper"
            >
              <Tab label="Welcome Page" {...a11yProps(0)} />
              <Tab label="SVG Animation" {...a11yProps(1)} />
              <Tab label="Canvas Animation" {...a11yProps(2)} />
              <Tab label="GreenSock Animation" {...a11yProps(3)} />
              <Tab label="P5 Animation" {...a11yProps(4)} />
            </Tabs>
          </AppBar>
          <SwipeableViews
            axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
            index={value}
            onChangeIndex={handleChangeIndex}
          >
            <TabPanel value={value} index={0} dir={theme.direction}>
              <ReactPage />
            </TabPanel>

            <TabPanel value={value} index={1} dir={theme.direction}>
              <SvgPage />
            </TabPanel>

            <TabPanel value={value} index={2} dir={theme.direction}>
              <CanvasPage />
             </TabPanel>

             <TabPanel value={value} index={3} dir={theme.direction}>
              <GsapPage />
             </TabPanel>

             <TabPanel value={value} index={4} dir={theme.direction}>
              <P5Page />
             </TabPanel>

          </SwipeableViews>
        </div>
      </div>
    </ThemeProvider>
  );
}

/*
  
*/

export default App;
