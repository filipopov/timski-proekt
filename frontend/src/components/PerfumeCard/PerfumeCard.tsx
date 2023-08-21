import React, { FC, ReactElement } from "react";
import { Button, Card, Col, Rate, Typography } from "antd";
import { Link } from "react-router-dom";
import Meta from "antd/lib/card/Meta";
import { DeleteOutlined, EditOutlined, ShoppingCartOutlined } from "@ant-design/icons";

import { PerfumeResponse } from "../../types/types";
import { ACCOUNT_ADMIN_PERFUMES, ACCOUNT_ADMIN_DELETE, PRODUCT } from "../../constants/routeConstants";
import { useCart } from "../../hooks/useCart";
import "./PerfumeCard.css";

type PropsType = {
    perfume: PerfumeResponse;
    colSpan: number;
    edit?: boolean;
    onOpenDelete?: (perfume: PerfumeResponse) => void;
};

const PerfumeCard: FC<PropsType> = ({ perfume, colSpan, edit, onOpenDelete }): ReactElement => {
    const { addToCart } = useCart(perfume.id);

    const onClickAddToCart = (event: any) => {
        event.preventDefault();
        addToCart();
    };

    return (
        <Col span={colSpan}>
            <Link to={`${PRODUCT}/${perfume.id}`}>
                <Card
                    className={"perfume-card"}
                    cover={<img className={"perfume-card-image"} alt={perfume.perfumeTitle} src={perfume.filename} />}
                    hoverable
                    actions={
                        edit
                            ? [
                                  <Link to={`${ACCOUNT_ADMIN_PERFUMES}/${perfume.id}`}>
                                      <Button icon={<EditOutlined />}>Измени</Button>
                                  </Link>,
                                  <Button icon={<DeleteOutlined />} onClick={() => onOpenDelete!(perfume)} danger>
                                      Delete
                                  </Button>
                              ]
                            : [
                                  <Button icon={<ShoppingCartOutlined />} onClick={onClickAddToCart}>
                                      Додади во кошничка
                                  </Button>
                              ]
                    }
                >
                    <Meta title={perfume.perfumeTitle} description={perfume.perfumer} style={{ textAlign: "center" }} />
                    <Typography.Text className={"perfume-card-price"}>{perfume.price}.00 MKD</Typography.Text>
                </Card>
            </Link>
        </Col>
    );
};

export default PerfumeCard;
