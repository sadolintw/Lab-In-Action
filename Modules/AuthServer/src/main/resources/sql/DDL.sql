
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_client_details](
	[client_id] [nvarchar](100) NOT NULL,
	[client_secret] [nvarchar](100) NULL,
	[web_server_redirect_uri] [nvarchar](100) NULL,
	[access_token_validity] [int] NULL,
	[refresh_token_validity] [int] NULL,
	[additional_information] [nvarchar](100) NULL,
	[autoapprove] [nchar](10) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nvarchar](100) NULL,
 CONSTRAINT [PK_oauth_client_details] PRIMARY KEY CLUSTERED 
(
	[client_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_client_grant_types](
	[id] [nvarchar](100) NOT NULL,
	[client_id] [nvarchar](100) NULL,
	[grant_type] [nvarchar](100) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nvarchar](100) NULL,
 CONSTRAINT [PK_oauth_client_grant_types] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_client_resource](
	[id] [nvarchar](100) NULL,
	[client_id] [nvarchar](100) NULL,
	[resource_id] [nvarchar](100) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nvarchar](100) NULL,
 CONSTRAINT [PK_oauth_client_resource] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_resource](
	[resource_id] [nvarchar](100) NOT NULL,
	[label] [nvarchar](100) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nvarchar](100) NULL,
 CONSTRAINT [PK_oauth_resource] PRIMARY KEY CLUSTERED 
(
	[resource_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_role_scope](
	[id] [nvarchar](100) NOT NULL,
	[rid] [nvarchar](100) NULL,
	[scope_id] [nvarchar](100) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nvarchar](100) NULL,
 CONSTRAINT [PK_oauth_role_scope] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_scope](
	[scope_id] [nvarchar](100) NOT NULL,
	[resource_id] [nvarchar](100) NULL,
	[scope_code] [nvarchar](100) NULL,
	[label] [nvarchar](100) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nchar](10) NULL,
 CONSTRAINT [PK_oauth_scope] PRIMARY KEY CLUSTERED 
(
	[scope_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[oauth_token](
	[id] [nvarchar](100) NULL,
	[token_id] [nvarchar](100) NULL,
	[refresh_id] [nvarchar](100) NULL,
	[client_id] [nvarchar](100) NULL,
	[grant_type] [nvarchar](100) NULL,
	[resource_ids] [nvarchar](100) NULL,
	[scopes] [nvarchar](100) NULL,
	[username] [nvarchar](100) NULL,
	[redirect_uri] [nvarchar](100) NULL,
	[access_token] [nvarchar](1000) NULL,
	[refresh_token] [nvarchar](1000) NULL,
	[refreshed] [tinyint] NULL,
	[locked] [tinyint] NULL,
	[authentication] [varbinary](max) NULL,
	[created_date] [datetime] NULL,
	[created_by] [nvarchar](100) NULL,
	[last_modified_date] [datetime] NULL,
	[last_modified_by] [nvarchar](100) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
