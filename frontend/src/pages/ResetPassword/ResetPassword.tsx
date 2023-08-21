import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { LockOutlined, ReloadOutlined, SyncOutlined } from "@ant-design/icons";
import { useHistory, useParams } from "react-router-dom";
import { Alert, Col, Divider, Form, Row } from "antd";

import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import ContentTitle from "../../components/ContentTitle/ContentTitle";
import { selectErrorMessage, selectErrors, selectUserAuthEmail } from "../../redux-toolkit/auth/auth-selector";
import { resetAuthState } from "../../redux-toolkit/auth/auth-slice";
import { fetchResetPasswordCode, resetPassword } from "../../redux-toolkit/auth/auth-thunks";
import FormInput from "../../components/FormInput/FormInput";
import IconButton from "../../components/IconButton/IconButton";

const ResetPassword: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const params = useParams<{ code: string }>();
    const history = useHistory();
    const userEmail = useSelector(selectUserAuthEmail);
    const errorMessage = useSelector(selectErrorMessage);
    const resetPasswordErrors = useSelector(selectErrors);
    const { passwordError, password2Error } = resetPasswordErrors;

    useEffect(() => {
        dispatch(resetAuthState());

        if (params.code) {
            dispatch(fetchResetPasswordCode(params.code));
        }
    }, []);

    const onClickReset = (data: { password: ""; password2: "" }): void => {
        const userResetPasswordData = { email: userEmail, ...data };
        dispatch(resetPassword({ request: userResetPasswordData, history }));
    };

    return (
        <ContentWrapper>
            <ContentTitle icon={<SyncOutlined />} title={"РЕСЕТИРАЈ ЛОЗИНКА"} />
            <Row gutter={32}>
                <Col span={12}>
                    <Form onFinish={onClickReset}>
                        <Divider />
                        {errorMessage && <Alert type="error" message={errorMessage} />}
                        <FormInput
                            title={"Лозинка:"}
                            icon={<LockOutlined />}
                            titleSpan={8}
                            wrapperSpan={16}
                            error={passwordError}
                            name={"password"}
                            placeholder={"Лозинка"}
                            inputPassword
                        />
                        <FormInput
                            title={"Потврди лозинка:"}
                            icon={<LockOutlined />}
                            titleSpan={8}
                            wrapperSpan={16}
                            error={password2Error}
                            name={"password2"}
                            placeholder={"Потврди лозинка"}
                            inputPassword
                        />
                        <IconButton title={"Ресетирај"} icon={<ReloadOutlined />} />
                    </Form>
                </Col>
            </Row>
        </ContentWrapper>
    );
};

export default ResetPassword;