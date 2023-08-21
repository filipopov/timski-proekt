import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { Card, Col, Row, Table } from "antd";
import { InfoCircleOutlined, ShoppingOutlined } from "@ant-design/icons";

import {
    selectIsOrderLoaded,
    selectIsOrderLoading,
    selectOrder,
    selectOrderItems
} from "../../../redux-toolkit/order/order-selector";
import { fetchOrderById, fetchOrderItemsByOrderId } from "../../../redux-toolkit/order/order-thunks";
import { resetOrderState } from "../../../redux-toolkit/order/order-slice";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import Spinner from "../../../components/Spinner/Spinner";
import AccountDataItem from "../../../components/AccountDataItem/AccountDataItem";
import { OrderItemResponse } from "../../../types/types";
import "./ManageUserOrder.css";

const ManageUserOrder: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const params = useParams<{ id: string }>();
    const order = useSelector(selectOrder);
    const orderItems = useSelector(selectOrderItems);
    const isOrderLoading = useSelector(selectIsOrderLoading);
    const isOrderLoaded = useSelector(selectIsOrderLoaded);
    const { id, email, firstName, lastName, totalPrice, postIndex, phoneNumber, date, city, address } = order;

    useEffect(() => {
        dispatch(fetchOrderById(params.id));

        return () => {
            dispatch(resetOrderState());
        };
    }, []);

    useEffect(() => {
        if (isOrderLoaded) {
            dispatch(fetchOrderItemsByOrderId(params.id));
        }
    }, [isOrderLoaded]);

    return (
        <>
            {isOrderLoading ? (
                <Spinner />
            ) : (
                <>
                    <div style={{ textAlign: "center" }}>
                        <ContentTitle title={`Порачка #${id}`} titleLevel={4} icon={<ShoppingOutlined />} />
                    </div>
                    <Row>
                        <Col span={24}>
                            <Card>
                                <Row gutter={32}>
                                    <Col span={12}>
                                        <InfoCircleOutlined className={"manage-user-icon"} />
                                        <ContentTitle title={"Информации за клиент"} titleLevel={5} />
                                        <AccountDataItem title={"Име"} text={firstName} />
                                        <AccountDataItem title={"Презиме"} text={lastName} />
                                        <AccountDataItem title={"Град"} text={city} />
                                        <AccountDataItem title={"Адреса"} text={address} />
                                        <AccountDataItem title={"Е-мејл"} text={email} />
                                        <AccountDataItem title={"Број"} text={phoneNumber} />
                                        <AccountDataItem title={"Поштенски број"} text={postIndex} />
                                    </Col>
                                    <Col span={12}>
                                        <InfoCircleOutlined className={"manage-user-icon"} />
                                        <ContentTitle title={"Информации за порачка"} titleLevel={5} />
                                        <AccountDataItem title={"ИД порачка"} text={id} />
                                        <AccountDataItem title={"Дата"} text={date} />
                                        <ContentTitle title={`Сума: ${totalPrice}.0 МКД`} titleLevel={4} />
                                    </Col>
                                </Row>
                                <Row style={{ marginTop: 16 }}>
                                    <Col span={24}>
                                        <Table
                                            rowKey={"id"}
                                            pagination={false}
                                            dataSource={orderItems}
                                            columns={[
                                                {
                                                    title: "ИД",
                                                    dataIndex: "id",
                                                    key: "id"
                                                },
                                                {
                                                    title: "Категорија",
                                                    dataIndex: "perfumer",
                                                    key: "perfumer",
                                                    render: (_, order: OrderItemResponse) => order.perfume.perfumer
                                                },
                                                {
                                                    title: "Име",
                                                    dataIndex: "perfumeTitle",
                                                    key: "perfumeTitle",
                                                    render: (_, order: OrderItemResponse) => order.perfume.perfumeTitle
                                                },
                                                {
                                                    title: "Количина",
                                                    dataIndex: "quantity",
                                                    key: "quantity"
                                                },
                                                {
                                                    title: "Цена",
                                                    dataIndex: "price",
                                                    key: "price",
                                                    render: (_, order: OrderItemResponse) => `${order.perfume.price}.0 МКД`
                                                },
                                                {
                                                    title: "Вкупно",
                                                    dataIndex: "amount",
                                                    key: "amount",
                                                    render: (_, order: OrderItemResponse) => `${order.amount}.0 МКД`
                                                }
                                            ]}
                                        />
                                    </Col>
                                </Row>
                            </Card>
                        </Col>
                    </Row>
                </>
            )}
        </>
    );
};

export default ManageUserOrder;
