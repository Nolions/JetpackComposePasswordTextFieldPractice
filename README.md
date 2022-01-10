# PasswordTextFieldPractice

Jetpack Compose練習Password Input Field

## OutlinedTextField

| 參數 | 型態  | 說明  |
| ---- | ---- | ---- |
| modifier | Modifier | 輸入欄位相關樣式設定 |
| value | String | 輸入欄位中的內容 |
| onValueChange | @Composable lambda | 輸入欄位內容變更時的Callback |
| placeholder | @Composable lambda | 輸入欄位在focus下的樣式 |
| keyboardOptions | KeyboardOptions | 軟體鍵盤相關設置 |
| keyboardActions | KeyboardActions | 點擊軟體鍵盤後的行為 |
| singleLine | Boolean | 是否單行顯式 |
| visualTransformation | VisualTransformation | 設定改變了輸入欄位的視覺效果 |
| trailingIcon | @Composable lambda | 輸入欄位後面中Icon |
| colors | TextFieldColors | 輸入欄位中相關顏色設置 |
| isError | Boolean | 是否為錯誤狀態 |
