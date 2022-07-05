import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Home from 'pages/Home';
import Navbar from 'components/Navbar';
//import Catalog from 'pages/Catalog';
//import Admin from 'pages/Admin';

function Routes() {
  return (
    <div>
      <BrowserRouter>
        <Navbar />
        <Switch>
          <Route path="/" exact>
            <Home />
          </Route>
          <Route path="/products"></Route>
          <Route path="/admin"></Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}
export default Routes;
