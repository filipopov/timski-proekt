import React, { FC, ReactElement } from "react";
import { Button, Col, Divider, Rate, Row, Space, Typography } from "antd";
import { ShoppingCartOutlined } from "@ant-design/icons";

import Description from "./Description/Description";
import { FullPerfumeResponse } from "../../../types/types";

type PropsType = {
    perfume?: Partial<FullPerfumeResponse>;
    reviewsLength: number;
    addToCart: () => void;
};

const ProductInfo: FC<PropsType> = ({ perfume, reviewsLength, addToCart }): ReactElement => {
    return (
        <Row>
            <Col span={12} className={"product-image-wrapper"}>
                <img src={"Мармалад од кајсија.jpeg"} alt={perfume?.perfumeTitle} className={"product-image"} />
            </Col>
            <Col span={12}>
                <Row className={"product-header"}>
                    <Col>
                        <Typography.Title level={3}>{perfume?.perfumeTitle}</Typography.Title>
                        <Typography.Title level={4}>{perfume?.perfumer}</Typography.Title>
                        <Typography.Text>{perfume?.type}</Typography.Text>
                    </Col>
                </Row>

                <Row>
                    <Typography.Text type="success">Достапен на залиха</Typography.Text>
                </Row>
                <Row style={{ marginTop: 16 }}>
                    <Col span={5}>
                        <Space align={"baseline"}>
                            <Typography.Text>{perfume?.price}.00 MKD</Typography.Text>
                        </Space>
                    </Col>
                    <Col span={4}>
                        <Button icon={<ShoppingCartOutlined />} onClick={addToCart}>
                            Додади во кошничка
                        </Button>
                    </Col>
                </Row>
                <Divider />
                <Row>
                    <Col span={8}>
                        <Description title={"Опис на продуктот:"} />
                    </Col>
                    <Col span={16}>
                        <Description title={perfume?.fragranceBaseNotes} />
                    </Col>
                </Row>
            </Col>
        </Row>
    );
};

export default ProductInfo;
