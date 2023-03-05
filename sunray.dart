  //raycast
  bdraw_line = false;
  if(in.kb[VK_LSHIFT] == Input::Hold || in.ms[1] == Input::Hold) 
  {
    bdraw_line = true;

    b2Vec2 mp = b2Vec2(in.mx*s2b,in.my*s2b);
    b2Vec2 bp = ball.body->GetPosition();
    b2Vec2 n = b2Vec2((mp.x-bp.x),(mp.y-bp.y));
    float force = n.Length();
    float brot = std::atan2(n.y,n.x);
    ball.body->SetTransform(bp,brot);
    
    if(in.kb[VK_SPACE] == Input::Down || in.ms[0] == Input::Down)
    {
        ball.body->ApplyLinearImpulse(b2Vec2(n.x*force,n.y*force), bp, true);
        createParticles(mp, 16);
    }
  }
}
void  Game::process(const Input& in)
{
  stdv_objs vobj2del;
  vobj2del.reserve(64);

  b2world->Step(1.0f/60, 10, 10);

  userInput(in);

  if(bdraw_line)
  {
		///shootRay.clear();
		///b2Vec2 v0 = ball.body->GetPosition();
		///b2Vec2 v1 = v0 + b2Mul(b2Rot(ball.body->GetAngle()), b2Vec2(s2b * win_dx, 0));
		///shootRayCast(v0, v1, 3);
    b2Vec2 v0 = ball.body->GetPosition();
    b2Vec2 v1 = v0 + b2Mul(b2Rot(ball.body->GetAngle()), b2Vec2(s2b * win_dx, 0));

    vline_x[0] = b2s*v0.x;
    vline_y[0] = b2s*v0.y;

    vline_x[1] = b2s*v1.x;
    vline_y[1] = b2s*v1.y;

    RaycastQuery rc_query;
    b2world->RayCast(&rc_query, v0, v1);
    if(rc_query.fraction_t < 1.0f)
    {
      vline_x[1] = b2s * rc_query.vpt.x;
      vline_y[1] = b2s * rc_query.vpt.y;
      
      //2 raycast
      v1.x = rc_query.vpt.x;
      v1.y = rc_query.vpt.y;

      //-2*(V dot N)*N + V
      b2Vec2 n = -2 * b2Dot(v1-v0, rc_query.normal) * rc_query.normal + (v1-v0);
      float rot = std::atan2(n.y,n.x);
      b2Vec2 v2 = v1 + b2Mul(b2Rot(rot), b2Vec2(s2b * win_dx, 0));

      vline_x[2] = b2s*v2.x;
      vline_y[2] = b2s*v2.y;

      RaycastQuery rc_query2;
      b2world->RayCast(&rc_query2, v1, v2);
      if(rc_query.fraction_t < 1.0f)
        {
          vline_x[2] = b2s * rc_query2.vpt.x;
          vline_y[2] = b2s * rc_query2.vpt.y;
        }

    }
  }