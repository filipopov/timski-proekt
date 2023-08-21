import React, { FC, ReactElement } from "react";
import {FacebookOutlined, InstagramOutlined, TwitterOutlined} from "@ant-design/icons";
import { Col, Row, Typography } from "antd";

import "./Footer.scss";

const Footer: FC = (): ReactElement => {
    return (
        <div className={"footer-wrapper"}>
            <Row >
                <Col span={12}>
                    <Typography.Title level={3}>Малешевски домашни производи</Typography.Title>
                    <Typography.Text>078 123 456</Typography.Text>
                    <Typography.Text className={"mt-12"}>Работно време од 08:00 до 20:00 часот.</Typography.Text>
                </Col>
                <Col span={12} >
                    <div className={"footer-wrapper-social"}>
                        <Typography.Title level={3}>Социјални медиуми</Typography.Title>
                        <a href="https://www.facebook.com/malesevskiProizvodiOfficial">
                            <FacebookOutlined />
                        </a>
                        <a href="#">
                            <InstagramOutlined />
                        </a>
                    </div>
                </Col>
            </Row>
        </div>
    );
};

export default Footer;
