import React, { FC, ReactElement } from "react";
import { Carousel } from "antd";
import { Link } from "react-router-dom";

import { PRODUCT } from "../../../constants/routeConstants";
import "./CarouselImageSlider.css";

export const sliderItems = [
    {
        id: "85",
        name: "Photo 1",
        url: "https://i.ibb.co/dkpHPXQ/1million-ENG.jpg"
    },
    {
        id: "46",
        name: "Photo 2",
        url: "img/326077083_718239963303468_6718091878739149465_n.jpg"
    }
];

const CarouselImageSlider: FC = (): ReactElement => {
    return (
        <Carousel>
        </Carousel>
    );
};

export default CarouselImageSlider;
