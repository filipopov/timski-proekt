import React, {FC, ReactElement, useEffect} from "react";

import CarouselImageSlider from "./CarouselImageSlider/CarouselImageSlider";
import PerfumeCardsSlider from "./PerfumeCardsSlider/PerfumeCardsSlider";

const Home: FC = (): ReactElement => {
    
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    
    return (
        <div>
            <CarouselImageSlider />
            <PerfumeCardsSlider />
        </div>
    );
};

export default Home;
