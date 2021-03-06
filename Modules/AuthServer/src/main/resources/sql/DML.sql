USE [LAB]
GO
INSERT [dbo].[oauth_client_details] ([client_id], [client_secret], [web_server_redirect_uri], [access_token_validity], [refresh_token_validity], [additional_information], [autoapprove], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'accountservice', N'$2a$10$WEqj0hYFK/eR6vik/NTeSO8t5JuVlOw/kl6maxXvrIQrG63Ui6Fna', N'https://google.com', 86400, 604800, N'{"scopeRangeBy":"role"}', NULL, CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
GO
INSERT [dbo].[oauth_client_grant_types] ([id], [client_id], [grant_type], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000001', N'accountservice', N'password', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
INSERT [dbo].[oauth_client_grant_types] ([id], [client_id], [grant_type], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000002', N'accountservice', N'refresh_token', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
INSERT [dbo].[oauth_client_grant_types] ([id], [client_id], [grant_type], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000003', N'accountservice', N'implicit', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
GO
INSERT [dbo].[oauth_client_resource] ([id], [client_id], [resource_id], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000001', N'accountservice', N'account', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
GO
INSERT [dbo].[oauth_resource] ([resource_id], [label], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'account', N'帳戶管理平台', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
GO
INSERT [dbo].[oauth_role_scope] ([id], [rid], [scope_id], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000001', N'0000000001', N'0000000001', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
INSERT [dbo].[oauth_role_scope] ([id], [rid], [scope_id], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000002', N'0000000001', N'0000000003', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
GO
INSERT [dbo].[oauth_scope] ([scope_id], [resource_id], [scope_code], [label], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000001', N'account', N'account', N'帳號存取', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
INSERT [dbo].[oauth_scope] ([scope_id], [resource_id], [scope_code], [label], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000002', N'account', N'account.readonly', N'帳號唯讀', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
INSERT [dbo].[oauth_scope] ([scope_id], [resource_id], [scope_code], [label], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000003', N'account', N'role', N'角色存取', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
INSERT [dbo].[oauth_scope] ([scope_id], [resource_id], [scope_code], [label], [created_date], [created_by], [last_modified_date], [last_modified_by]) VALUES (N'0000000004', N'account', N'role.readonly', N'角色唯讀', CURRENT_TIMESTAMP, N'admin', CURRENT_TIMESTAMP, N'admin')
GO
