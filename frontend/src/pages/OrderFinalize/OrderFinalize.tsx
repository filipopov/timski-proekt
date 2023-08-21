import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Typography } from "antd";

import { selectOrder } from "../../redux-toolkit/order/order-selector";
import { resetCartState } from "../../redux-toolkit/cart/cart-slice";
import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";

const OrderFinalize: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const order = useSelector(selectOrder);

    useEffect(() => {
        dispatch(resetCartState());
    }, []);

    return (
        <ContentWrapper>
            <div style={{ textAlign: "center" }}>
                <Typography.Title level={2}>Ви благодариме за вашата порачка!</Typography.Title>
                <Typography.Text>Вашиот број на порачка е: {order.id}</Typography.Text>
            </div>
        </ContentWrapper>
    );
};

export default OrderFinalize;