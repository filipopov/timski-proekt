import React, { FC, ReactElement, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Col, Form, Row } from "antd";
import { CheckOutlined, EditOutlined, EyeInvisibleOutlined, ProfileOutlined } from "@ant-design/icons";

import { selectUserEditErrors, selectUserFromUserState } from "../../../redux-toolkit/user/user-selector";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import AccountDataItem from "../../../components/AccountDataItem/AccountDataItem";
import FormInput from "../../../components/FormInput/FormInput";
import IconButton from "../../../components/IconButton/IconButton";
import { updateUserInfo } from "../../../redux-toolkit/user/user-thunks";
import { resetInputForm } from "../../../redux-toolkit/user/user-slice";

interface PersonalData {
    firstName: string;
    lastName: string;
    city: string;
    address: string;
    phoneNumber: string;
    postIndex: string;
}

const PersonalData: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const usersData = useSelector(selectUserFromUserState);
    const errors = useSelector(selectUserEditErrors);
    const [showUserData, setShowUserData] = useState<boolean>(false);
    const { firstNameError, lastNameError } = errors;

    const onClickShowUserData = (): void => {
        setShowUserData((prevState) => !prevState);
    };

    useEffect(() => {
        dispatch(resetInputForm());

        if (usersData) {
            form.setFieldsValue(usersData);
        }
    }, []);

    const onFormSubmit = (data: PersonalData): void => {
        dispatch(updateUserInfo({ id: usersData?.id, ...data }));
    };

    return (
        <>
            <ContentTitle title={"My Account"} titleLevel={4} icon={<ProfileOutlined />} />
            <Row>
                <Col span={12}>
                    <AccountDataItem title={"Е-мејл"} text={usersData?.email} />
                    <AccountDataItem title={"Име"} text={usersData?.firstName} />
                    <AccountDataItem title={"Презиме"} text={usersData?.lastName} />
                    <AccountDataItem title={"Град"} text={usersData?.city} />
                    <AccountDataItem title={"Адреса"} text={usersData?.address} />
                    <AccountDataItem title={"Број"} text={usersData?.phoneNumber} />
                    <AccountDataItem title={"Поштенски број"} text={usersData?.postIndex} />
                    <Button
                        type={"primary"}
                        onClick={onClickShowUserData}
                        icon={showUserData ? <EyeInvisibleOutlined /> : <EditOutlined />}
                    >
                        {showUserData ? "Сокриј" : "Измени"}
                    </Button>
                </Col>
                <Col span={12}>
                    {showUserData && (
                        <Form onFinish={onFormSubmit} form={form}>
                            <FormInput
                                title={"Име:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"firstName"}
                                error={firstNameError}
                                placeholder={"First name"}
                            />
                            <FormInput
                                title={"Презиме:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"lastName"}
                                error={lastNameError}
                                placeholder={"Last name"}
                            />
                            <FormInput
                                title={"Град:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"city"}
                                placeholder={"City"}
                            />
                            <FormInput
                                title={"Адреса:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"address"}
                                placeholder={"Address"}
                            />
                            <FormInput
                                title={"Број:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"phoneNumber"}
                                placeholder={"Phone number"}
                            />
                            <FormInput
                                title={"Поштенски број:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"postIndex"}
                                placeholder={"Post index"}
                            />
                            <IconButton title={"Зачувај"} icon={<CheckOutlined />} />
                        </Form>
                    )}
                </Col>
            </Row>
        </>
    );
};

export default PersonalData;
