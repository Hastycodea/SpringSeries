import Announcement from "../../components/announcement/Announcement";
import Categories from "../../components/categories/Categories";
import Navbar from "../../components/navbar/Navbar";
import BestSellers from "../../components/products/BestSellers";
import NewArrivals from "../../components/products/NewArrivals";
import Slider from "../../components/slider/Slider";

const Home = () => {
  return (
    <div>
      {/* <Announcement /> */}
      <Navbar/>
      <Slider />
      <Categories />
      <NewArrivals />
      <BestSellers />
    </div>
  );
};

export default Home;
