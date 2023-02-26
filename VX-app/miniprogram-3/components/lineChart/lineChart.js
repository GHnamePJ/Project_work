Component({
  externalClasses: ['line-class'],
  properties: {
    width: String,
    height: String,
    data: Array,
  },
  observers: {
    width() {
      // 这里监听 width 变化重绘 canvas
      // 动态传入 width 好像只能这样了..
      const query = this.createSelectorQuery();
      query
        .select('#line')
        .fields({
          node: true,
          size: true
        })
        .exec(res => {
          const canvas = res[0].node;
          const ctx = canvas.getContext('2d');
          const width = res[0].width; // 画布宽度
          const height = res[0].height; // 画布高度
          // console.log(`宽度: ${width}, 高度: ${height}`);
          const dpr = wx.getSystemInfoSync().pixelRatio;
          canvas.width = width * dpr;
          canvas.height = height * dpr;
          ctx.scale(dpr, dpr);



          // 开始绘图
          this.drawLine(ctx, width, height, this.data.data);
        });

    },

  },

  methods: {
    drawLine(ctx, width, height, data) {
      const Max = Math.max(...data);
      const Min = Math.min(...data);

      // 把 canvas 的宽度, 高度按一定规则平分
      const startX = width / (data.length * 2), // 起始点的横坐标 X
        baseY = height * 0.9, // 基线纵坐标 Y
        diffX = width / data.length,
        diffY = (height * 0.7) / (Max - Min); // 高度预留 0.2 写温度
      ctx.beginPath();
      ctx.textAlign = 'center';
      ctx.font = '13px Microsoft YaHei';
      ctx.lineWidth = 2;
      ctx.strokeStyle = '#ABDCFF';

      // 画折线图的线
      data.forEach((item, index) => {
        const x = startX + diffX * index,
          y = baseY - (item - Min) * diffY;
        ctx.fillText(`${item}℃`, x, y - 10);
        ctx.lineTo(x, y);
      });
      ctx.stroke();


      // 画折线图背景
      ctx.lineTo(startX + (data.length - 1) * diffX, baseY); // 基线终点
      ctx.lineTo(startX, baseY); // 基线起点
      const lingrad = ctx.createLinearGradient(0, 0, 0, height * 0.7);
      lingrad.addColorStop(0, 'rgba(255,255,255,0.9)');
      lingrad.addColorStop(1, 'rgba(171,220,255,0)');
      ctx.fillStyle = lingrad;
      ctx.fill();



      // 画折线图上的小圆点
      ctx.beginPath();
      data.forEach((item, index) => {
        const x = startX + diffX * index,
          y = baseY - (item - Min) * diffY;

        ctx.moveTo(x, y);
        ctx.arc(x, y, 3, 0, 2 * Math.PI);
      });
      ctx.fillStyle = '#0396FF';
      ctx.fill();
    },
  },
});