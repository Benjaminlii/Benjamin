# Java中使用外部接口

​		Java语言有多种调用外部接口的方法,最基础版为JDK支持的使用IO流和网络编程部分技术实现.Apache对Java JDK对这个功能的实现进行的封装,形成了Apache httpclient 3(org.apache.commons.httpclient)和Apache httpclient 4(org.apache.http.client).二者是一个技术,版本不一样,3版本中还以工具类的方式存在,4版本中独立为一个包.

​		开发中常使用Apache httpclient 4实现使用外部接口的功能.

## JDK方式

​		JDK中IO流以及网络部分提供了方法实现网络请求的发送.主要使用URL对象建立网络连接,设置链接的参数后获取输入流,将输入流读入本地的StringBuffer(或其他)中返回即可.

​		post请求还需要将参数(字符串)通过流发送出去,其他无二.

### get请求

​		get请求将请求体写在URL后面,所以不需要额外的输入参数.

```java
/**
 * GET请求
 */
public static String doGet(String httpUrl) {
    HttpURLConnection connection = null;
    InputStream is = null;
    BufferedReader br = null;
    String result = null;//返回结果字符串

    try {
        //创建远程url连接对象
        URL url = new URL(httpUrl);
        //通过远程url连接对象打开一个连接，强转成httpURLConnection类
        connection = (HttpURLConnection) url.openConnection();
        //设置连接方式：get
        connection.setRequestMethod("GET");
        //设置连接主机服务器的超时时间：15000毫秒
        connection.setConnectTimeout(15000);
        //设置读取远程返回的数据时间：60000毫秒
        connection.setReadTimeout(60000);

        //通过connection连接，获取输入流
        is = connection.getInputStream();
        //封装输入流is，并指定字符集
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        //存放数据
        StringBuffer sbf = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sbf.append(temp);
            sbf.append("\r\n");//回车+换行
        }

        result = sbf.toString();

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //关闭资源
        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        connection.disconnect();//关闭远程连接
    }

    return result;
}
```

### post请求

​		要额外设置输出参数,并将参数通过流输出到目标的URL中.

```java
    /**
     * POST 请求
     */
    public static String doPost(String httpUrl, String param) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;

        try {
            //创建远程url连接对象
            URL url = new URL(httpUrl);
            //通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            //设置连接请求方式
            connection.setRequestMethod("POST");
            //设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            //设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            //默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            //默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);

            //通过连接对象获取一个输出流
            os = connection.getOutputStream();
            //通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());//把需要传送的参数发送给远程url

            //通过连接对象获取一个输入流，向远程读取
            is = connection.getInputStream();
            //对输入流对象进行包装
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            //循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            result = sbf.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
```

## Apache httpclient 3

### get请求

```java
/**
 * GET 请求方法
 * 注：如果需要传递参数，把参数拼接在url地址后面
 */
public static String doGet(String url) {
    //输入流
    InputStream is = null;
    BufferedReader br = null;
    String result = null;

    //创建httpClient实例
    HttpClient httpClient = new HttpClient();

    //设置http连接主机服务超时时间：15000毫秒
    //先获取连接管理器对象，再获取参数对象,再进行参数的赋值
    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

    //创建一个Get方法实例对象
    GetMethod getMethod = new GetMethod(url);
    //设置get请求超时为60000毫秒
    getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
    //设置请求重试机制，默认重试次数：3次，参数设置为true，重试机制可用，false相反
    getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));


    try {
        //执行Get方法
        int statusCode = httpClient.executeMethod(getMethod);
        //判断返回码
        if(statusCode != HttpStatus.SC_OK) {
            //如果状态码返回的不是ok,说明失败了,打印错误信息
            System.err.println("Method faild: " + getMethod.getStatusLine());
        }

        //通过getMethod实例，获取远程的一个输入流
        is = getMethod.getResponseBodyAsStream();
        //包装输入流
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        StringBuffer sbf = new StringBuffer();
        //读取封装的输入流
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sbf.append(temp).append("\r\n");
        }

        result = sbf.toString();

    } catch (HttpException e) {
        System.err.println("Fatal protocol violation: " + e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("Fatal transport error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        //关闭资源
        if(null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //释放连接
        getMethod.releaseConnection();
    }

    return result;
}
```

### post请求

```java
/**
	 * POST 请求方法
	 */
public static String doPost(String url, Map<String,Object> paramMap) throws UnsupportedEncodingException {
    //获取输入流
    InputStream is = null;
    BufferedReader br = null;
    String result = null;

    //创建httpClient实例对象
    HttpClient httpClient = new HttpClient();
    //设置httpClient连接主机服务器超时时间：15000毫秒
    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

    //创建post请求方法实例对象
    PostMethod postMethod = new PostMethod(url);
    //设置post请求超时时间
    postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

    NameValuePair[] nvp = null;
    //判断参数map集合paramMap是否为空
    if(null != paramMap && paramMap.size() > 0) {//不为空
        //创建键值参数对象数组，大小为参数的个数
        nvp = new NameValuePair[paramMap.size()];
        //循环遍历参数集合map
        Set<Entry<String, Object>> entrySet = paramMap.entrySet();
        //获取迭代器
        Iterator<Entry<String, Object>> iterator = entrySet.iterator();

        int index = 0;
        while(iterator.hasNext()) {
            Entry<String, Object> mapEntry = iterator.next();
            //从mapEntry中获取key和value创建键值对象存放到数组中
            nvp[index] = new NameValuePair(mapEntry.getKey(), new String(mapEntry.getValue().toString().getBytes("UTF-8"),"UTF-8"));
            index++;
        }
    }

    //判断nvp数组是否为空
    if(null != nvp && nvp.length > 0) {
        //将参数存放到requestBody对象中
        postMethod.setRequestBody(nvp);
    }

    try {
        //执行POST方法
        int statusCode = httpClient.executeMethod(postMethod);
        //判断是否成功
        if(statusCode != HttpStatus.SC_OK) {
            System.err.println("Method faild: " + postMethod.getStatusLine());
        }

        //获取远程返回的数据
        is = postMethod.getResponseBodyAsStream();
        //封装输入流
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        StringBuffer sbf = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sbf.append(temp).append("\r\n");
        }

        result = sbf.toString();

    } catch (HttpException e) {
        System.err.println("Fatal protocol violation: " + e.getMessage());
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("Fatal transport error: " + e.getMessage());
        e.printStackTrace();
    } finally {

        //关闭资源
        if(null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //释放连接
        postMethod.releaseConnection();
    }

    return result;
}
```

## Apache httpclient 4

### get请求

```java
/**
 * Get 请求方法
 */
public static String doGet(String url) {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    String result = "";

    try {
        //通过址默认配置创建一个httpClient实例
        httpClient = HttpClients.createDefault();
        //创建httpGet远程连接实例
        HttpGet httpGet = new HttpGet(url);
        //设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(35000)//连接主机服务超时时间
            .setConnectionRequestTimeout(35000)//请求超时时间
            .setSocketTimeout(60000)//数据读取超时时间
            .build();
        //为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        //执行get请求得到返回对象
        response = httpClient.execute(httpGet);
        //通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        //通过EntityUtils中的toString方法将结果转换为字符串
        result = EntityUtils.toString(entity);

    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //关闭资源
        if(null != response) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    return result;
}
```

### post请求

​		这里默认使用UTF-8编码,如需更改传入参数设置即可.

```java
public static String doPost(String url, Map<String,Object> paramMap) {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    String result = "";

    try {
        //创建httpClient实例
        httpClient = HttpClients.createDefault();
        //创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        //配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(35000)//设置连接主机服务超时时间
            .setConnectionRequestTimeout(35000)//设置连接请求超时时间
            .setSocketTimeout(60000)//设置读取数据连接超时时间
            .build();
        //为httpPost实例设置配置
        httpPost.setConfig(requestConfig);

        //封装post请求参数
        if(null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            //通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            //循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            //为httpPost设置封装好的请求参数
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        }

        //执行post请求得到返回对象
        response = httpClient.execute(httpPost);
        //通过返回对象获取数据
        HttpEntity entity = response.getEntity();
        //将返回的数据转换为字符串
        result = EntityUtils.toString(entity);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        //关闭资源
        if(null != response) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result;
}
```

