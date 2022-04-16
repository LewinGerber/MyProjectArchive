import { TitleView, Content, FooterInfo } from './components/structure.jsx';
import { GeoGebraView } from './components/geogebraView.jsx';
import './App.css';

export default function App() {
  return (
    <div>
      <TitleView />
      <Content id="content"/>
      <GeoGebraView />
      <FooterInfo />
    </div>
  );
}
