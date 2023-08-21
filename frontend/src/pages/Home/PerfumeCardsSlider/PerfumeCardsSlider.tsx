import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Carousel, Typography } from "antd";

import { selectPerfumes } from "../../../redux-toolkit/perfumes/perfumes-selector";
import { fetchPerfumesByIds } from "../../../redux-toolkit/perfumes/perfumes-thunks";
import { resetPerfumesState } from "../../../redux-toolkit/perfumes/perfumes-slice";
import PerfumeCardsSliderItem from "./PerfumeCardsSliderItem/PerfumeCardsSliderItem";
import "./PerfumeCardsSlider.css";

export const perfumesIds = [26, 17, 65, 24, 10, 20, 21, 23];

const PerfumeCardsSlider: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const perfumes = useSelector(selectPerfumes);

    useEffect(() => {
        // GraphQL example
        // dispatch(fetchPerfumesByIdsQuery(perfumesId));
        dispatch(fetchPerfumesByIds(perfumesIds));

        return () => {
            dispatch(resetPerfumesState());
        };
    }, []);

    return (
        <div className={"perfume-cards-slider"}>
            <Typography.Title level={3} className={"perfume-cards-slider-title"}>
                ПОПУЛАРНИ ПРОДУКТИ
            </Typography.Title>
            <Carousel>
                <PerfumeCardsSliderItem perfumes={perfumes.slice(0, 4)} />
                <PerfumeCardsSliderItem perfumes={perfumes.slice(4, 8)} />
            </Carousel>
        </div>
    );
};

export default PerfumeCardsSlider;
