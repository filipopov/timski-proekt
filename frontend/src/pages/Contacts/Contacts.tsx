import React, {FC, ReactElement, useEffect} from "react";
import { Col, Row, Typography } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";

import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import ContentTitle from "../../components/ContentTitle/ContentTitle";

const Contacts: FC = (): ReactElement => {

    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    
    return (
        <ContentWrapper>
            <ContentTitle icon={<InfoCircleOutlined />} title={"Контакт"} />
            <Row gutter={32}>
                <Col span={12}>
                    <div>
                        <Typography.Text strong>{"Број: "}</Typography.Text>
                        <Typography.Text>078 123 456</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"Е-мејл: "}</Typography.Text>
                        <Typography.Text>malesevskidomasniproizvodi@gmail.com</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Работно време</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>
                            Онлајн продавницата е отворена од 08:00 до 20:00 часот.  <br />Онлајн нарачките се прифатени во најкраток можен рок.
                        </Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Достава</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>Доставата на нарачки е извршена преку карго служба до вашиот дом.</Typography.Text>
                    </div>
                </Col>
            </Row>
        </ContentWrapper>
    );
};

export default Contacts;
